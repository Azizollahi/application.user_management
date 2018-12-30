module service {
	requires spring.context;
	requires infrastructure.repository;
	requires service.interfaces;
	requires domain;
	requires service.exceptions;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires spring.beans;
	requires spring.security.jwt;
	requires spring.data.commons;
	exports service;
}