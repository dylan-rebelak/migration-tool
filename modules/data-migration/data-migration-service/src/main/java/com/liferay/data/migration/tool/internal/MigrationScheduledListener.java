package com.liferay.data.migration.tool.internal;

import com.liferay.data.migration.tool.service.MigrationHandler;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.lock.LockManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(immediate = true, service = MigrationScheduledListener.class)
public class MigrationScheduledListener
	extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties)
		throws SchedulerException {

		String cronExpression = GetterUtil.getString(
			properties.get("cron.expression"), _DEFAULT_CRON_EXPRESSION);

		Trigger jobTrigger = TriggerFactoryUtil.createTrigger(
			getEventListenerClass(), getEventListenerClass(), new Date(),
			cronExpression);

		schedulerEntryImpl = new StorageAwareSchedulerEntryImpl(
			schedulerEntryImpl);

		schedulerEntryImpl.setTrigger(jobTrigger);

		if (_initialized) {
			deactivate();
		}

		_schedulerEngineHelper.register(
			this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);

		_initialized = true;
	}

	@Deactivate
	protected void deactivate() {
		try {
			_schedulerEngineHelper.unschedule(
				schedulerEntryImpl,
				((StorageTypeAware)schedulerEntryImpl).getStorageType());
		}
		catch (SchedulerException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(">>> Unable to unschedule trigger", se);
			}
		}

		_schedulerEngineHelper.unregister(this);

		_initialized = false;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		Lock lock = null;

		String className = MigrationScheduledListener.class.toString();

		className = className.substring(className.indexOf("com"));

		String user = "Scheduled Task";

		if (_lockManager.isLocked(className, className)) {
			if (_log.isDebugEnabled()) {
				_log.debug(">>> Migration is locked and already running");
			}

			return;
		}

		lock = _lockManager.lock(className, className, user);

		if (_log.isDebugEnabled()) {
			_log.debug(">>> Acquired lock for Data Migration");
		}

		try {
			_migrationManagerService.runMigration();
		}
		finally {
			_lockManager.unlock(lock.getClassName(), lock.getKey());

			if (_log.isDebugEnabled()) {
				_log.debug(">>> Released lock for Data Migration");
			}
		}
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}

	private static final String _DEFAULT_CRON_EXPRESSION = "0 0 0 * * ?";

	private static final Log _log = LogFactoryUtil.getLog(
		MigrationScheduledListener.class);

	private volatile boolean _initialized;

	@Reference
	private LockManager _lockManager;

	@Reference
	private MigrationHandler _migrationManagerService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

}