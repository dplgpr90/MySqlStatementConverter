package service;

import java.io.Reader;

import model.Table;

public interface Parser {

	public Table parse(Reader r);

}