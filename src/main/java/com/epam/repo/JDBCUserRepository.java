package com.epam.repo;

import com.epam.exception.UserException;
import com.epam.exception.UserNotFoundException;
import com.epam.model.User;
import com.epam.repo.jdbc.ConnectionPoolProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class JDBCUserRepository implements UserRepository {

	/*
	 * CREATE TABLE user_table ( id SERIAL PRIMARY KEY, first_name varchar(255),
	 * last_name varchar(255) );
	 */

	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUserRepository.class);

	private static JDBCUserRepository self;

	private JDBCUserRepository() throws SQLException {
	}

	public static synchronized JDBCUserRepository getInstance() throws SQLException {
		if (self == null) {
			self = new JDBCUserRepository();
		}
		return self;
	}

	@Override
	public User getById(Integer id) {
		String getById = "SELECT * FROM user_table WHERE id = ".concat(id.toString());
		try (Connection connection = ConnectionPoolProvider.getConnection()) {

			ResultSet resultSet = connection.createStatement().executeQuery(getById);

			if (resultSet.next()) {

				User user = mapUser(resultSet);
				return user;
			} else {
				return null;
			}

		} catch (SQLException e) {
			LOGGER.error("Something went wrong during user retrieval by id=" + id, e);
			throw new UserNotFoundException(id, e);
		}
	}

	@Override
	public Collection<User> getAll() {
		String getById = "SELECT * FROM user_table";
		try (Connection connection = ConnectionPoolProvider.getConnection()) {

			ResultSet resultSet = connection.createStatement().executeQuery(getById);

			List<User> users = new ArrayList<>();

			while (resultSet.next()) {
				users.add(mapUser(resultSet));
			}

			return users;

		} catch (SQLException e) {
			LOGGER.error("Something whent wrong during users retrieval", e);
			throw new UserException(e);
		}
	}

	@Override
	public User save(User user) {
		String insertUserSQL = "INSERT INTO user_table (first_name,last_name) VALUES (?,?)";

		try (Connection connection = ConnectionPoolProvider.getConnection();
				PreparedStatement ps = connection.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());

			if (ps.executeUpdate() != 1) {
				throw new UserException("User was not created");
			}

			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				user.setId(generatedKeys.getInt(1));
			}
			return user;

		} catch (SQLException e) {
			LOGGER.error("Something whent wrong during users retrieval", e);
			throw new UserException(e);
		}

	}

	@Override
	public void removeAll() {
		String deleteAll = "DELETE FROM user_table";

		try (Connection connection = ConnectionPoolProvider.getConnection();
				Statement ps = connection.createStatement()) {

			ps.execute(deleteAll);

		} catch (SQLException e) {
			LOGGER.error("Something whent wrong during users deletion", e);
			throw new UserException(e);
		}
	}

	private User mapUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setLastName(resultSet.getString("last_name"));
		return user;
	}

	@Override
	public void delete(Integer id) {
		String deleteById = "DELETE FROM user_table WHERE id=" + id;

		try (Connection connection = ConnectionPoolProvider.getConnection();
				Statement ps = connection.createStatement()) {

			ps.execute(deleteById);

		} catch (SQLException e) {
			LOGGER.error("Something whent wrong during user " + id + " deletion", e);
			throw new UserException(e);
		}

	}

}
