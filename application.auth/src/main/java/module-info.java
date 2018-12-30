module application.auth {
	requires javax.servlet.api;
	requires spring.webmvc;
	requires spring.beans;
	requires spring.context;
	requires spring.web;
	requires spring.core;
	requires service.interfaces;
	requires service.auth.interfaces;
	requires domain;
	requires service.exceptions;
	requires domain.exception;

	requires guava;

	exports application.auth.interceptors;
}