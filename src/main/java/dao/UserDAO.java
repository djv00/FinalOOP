package dao;

import model.UserDTO;

/**
 * DAO interface for user operations: registration and authentication.
 * Maps to 'users' table.
 * Applies DAO pattern for FR-01.
 *
 * @author Kai Lu
 */
public interface UserDAO {

    /**
     * Registers a new user.
     * @param user UserDTO to insert
     * @return true if success, false if email already exists
     * @throws Exception on database error
     */
    boolean registerUser(UserDTO user) throws Exception;

    /**
     * Finds user by email and password.
     * @param email email
     * @param password password
     * @return matching UserDTO or null
     * @throws Exception on error
     */
    UserDTO login(String email, String password) throws Exception;

    /**
     * Checks if email already exists.
     * @param email email to check
     * @return true if exists
     * @throws Exception on error
     */
    boolean emailExists(String email) throws Exception;
}
