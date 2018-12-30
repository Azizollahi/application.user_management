module domain {
	requires spring.data.jpa;
	requires domain.exception;
	requires guava;
	requires java.persistence;
	exports domain;
	exports domain.management;
}