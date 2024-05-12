package org.example;

import org.chapter4.Pipeline;
import org.chapter5.DataInOut;
import org.chapter5.FileInOut;
import org.chapter6.jdbcTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        jdbcTest test = new jdbcTest();
        test.excute();
    }
}