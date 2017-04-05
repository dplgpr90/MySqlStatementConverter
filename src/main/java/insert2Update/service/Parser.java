package main.java.insert2Update.service;

import java.io.Reader;

import main.java.insert2Update.model.Statement;

public interface Parser {

	public Statement insert2Update(Reader r);

}