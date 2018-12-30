module application.config {
	requires service.auth.interfaces;
	requires service.auth;
	requires spring.context;
	requires spring.web;
	requires application.auth;
	requires spring.data.jpa;
	requires spring.boot.autoconfigure;
	requires infrastructure.repository;
	requires spring.beans;
	requires service.management.interfaces;
	requires service.management;
	requires service;
	requires service.interfaces;
	requires infrastructure.cryptography;
	requires infrastructure.cryptography.interfaces;
	requires spring.webmvc;

	exports application.config.beans;
}