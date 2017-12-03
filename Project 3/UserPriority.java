package DataStructures;

public enum UserPriority {

HIGH(1), MEDIUM(2), LOW(3);

private int value;

public int getValue() {

return value;

}

private UserPriority(int value) {

this.value = value;

}

}