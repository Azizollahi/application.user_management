module service.management {
	requires domain;
	requires service.management.interfaces;
	requires infrastructure.repository;
	requires spring.beans;
	requires spring.context;
	requires spring.data.commons;
	exports service.management;
}