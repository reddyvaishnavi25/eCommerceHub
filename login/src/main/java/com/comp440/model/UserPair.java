package com.comp440.model;

public class UserPair {
String UserA;
String UserB;
public UserPair(String userA, String userB) {
	super();
	UserA = userA;
	UserB = userB;
}
public String getUserA() {
	return UserA;
}
public void setUserA(String userA) {
	UserA = userA;
}
public String getUserB() {
	return UserB;
}
public void setUserB(String userB) {
	UserB = userB;
}

}
