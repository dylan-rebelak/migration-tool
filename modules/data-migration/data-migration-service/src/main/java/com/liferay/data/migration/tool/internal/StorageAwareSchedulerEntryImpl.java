package com.liferay.data.migration.tool.internal;

import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.Trigger;

/**
 * @author Dylan Rebelak
 */
public class StorageAwareSchedulerEntryImpl
	extends SchedulerEntryImpl implements StorageTypeAware {

	public StorageAwareSchedulerEntryImpl(SchedulerEntryImpl delegate) {
		_delegate = delegate;
	}

	@Override
	public String getDescription() {
		return _delegate.getDescription();
	}

	@Override
	public String getEventListenerClass() {
		return _delegate.getEventListenerClass();
	}

	@Override
	public StorageType getStorageType() {
		return StorageType.PERSISTED;
	}

	@Override
	public Trigger getTrigger() {
		return _delegate.getTrigger();
	}

	@Override
	public void setDescription(String description) {
		_delegate.setDescription(description);
	}

	@Override
	public void setEventListenerClass(String eventListenerClass) {
		_delegate.setEventListenerClass(eventListenerClass);
	}

	@Override
	public void setTrigger(Trigger trigger) {
		_delegate.setTrigger(trigger);
	}

	private SchedulerEntryImpl _delegate;

}