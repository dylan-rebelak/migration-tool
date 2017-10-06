# Parallel Migration tool
As seen at Liferay DevCon 2017

This project is a work in progress and aims to be a convenient way to migrate data quickly and efficiently from one system to another.

In the current state, one only needs to implement a component of the EntityService interface in order to use the tool.
There is a sample module that shows how this can be done with a contrived example using the User service in Liferay.

## Planned Features
- [ ] Move configurable settings to OSGI configuration instead of portal properties
- [ ] Add control panel dashboard to monitor statistics (currently all saved in database, no UI)
- [ ] Release packaged version (.lpkg) to Marketplace
- [ ] Create producer-consumer message based architecture
