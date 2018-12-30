module service.auth.interfaces {
	requires domain;
	requires domain.exception;
	requires service.exceptions;
	requires infrastructure.repository;
	requires javax.servlet.api;

	exports service.auth.interfaces;
}