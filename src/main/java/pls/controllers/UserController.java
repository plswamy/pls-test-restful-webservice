package pls.controllers;

import pls.models.User;
import pls.models.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class UserController
 */
@Controller
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Create a new user with an auto-generated id and email and name as passed
   * values.
   */
  @RequestMapping(value="/create")
  @ResponseBody
  public String create(String email, String name) {
    try {
      User user = new User(email, name);
      userDao.create(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created!";
  }

  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }

  /**
   * Update the email and the name for the user indentified by the passed id.
   */
  @RequestMapping(value="/update")
  @ResponseBody
  public String updateName(long id, String email, String name) {
    try {
      User user = userDao.getById(id);
      user.setEmail(email);
      user.setName(name);
      userDao.update(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }

  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="/findByEmail")
  @ResponseBody
  public String findByEmail(String email) {
    String userId;
    String userName;
    try {
      User user = userDao.getByEmail(email);
      userId = String.valueOf(user.getId());
      userName=user.getName();
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user id is: " + userId+"  for username : "+userName;
  }

  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="/findById")
  @ResponseBody
  public String findById(long id) {
    String userId;
    String userName;
    try {
      User user = userDao.getById(id);
      if(user!=null){
      userId = String.valueOf(user.getId());
      userName=user.getName();
      }else{
        return "User with id "+id+" not found";
      }
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user id is: " + userId+"  for username : "+userName;
  }



  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  // Wire the UserDao used inside this controller.
  @Autowired
  private UserDao userDao;

} // class UserController
