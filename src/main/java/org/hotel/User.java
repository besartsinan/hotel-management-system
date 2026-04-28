package org.hotel;

public class User {

    private String userId;
    private String name;
    private String role;

    public User(String userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public String buildGreeting(char[] template) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < template.length; i++) {
            if (template[i] == '*') {
                result.append(role.charAt(0));
            } else if (template[i] == '#') {
                result.append(name.charAt(0));
            } else {
                result.append(template[i]);
            }
        }
        return result.toString();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}