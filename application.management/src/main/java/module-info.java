module application.management {
	requires javax.servlet.api;
	requires spring.webmvc;
	requires spring.beans;
	requires spring.context;
	requires spring.web;
	requires spring.core;
	requires service.interfaces;
	requires service.management.interfaces;
	requires domain;
	requires service.exceptions;
	requires domain.exception;
	requires framework;

	exports application.management.controllers;
	exports application.management.models;
}