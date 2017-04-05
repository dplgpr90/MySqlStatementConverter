package service;

import java.io.Reader;

import model.Statement;

public interface Parser {

	public Statement insert2Update(Reader r);

}