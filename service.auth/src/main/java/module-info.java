module service.auth {
	requires spring.context;
	requires spring.beans;
	requires service.auth.interfaces;
	requires domain;
	requires domain.exception;
	requires service.exceptions;
	requires infrastructure.repository;
	requires framework;
	requires service.interfaces;
	requires infrastructure.cryptography.interfaces;

	requires guava;
	requires spring.data.commons;
	requires spring.security.jwt;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	exports service.auth;
}