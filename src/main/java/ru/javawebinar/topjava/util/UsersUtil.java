package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UsersUtil {

    public static final List<User> users = Arrays.asList(
            new User ("Misha", "flyercombat@mail.ru", "abc", Role.USER),
            new User ("Grisha", "gregory@mail.ru", "ded", Role.USER),
            new User ("Sasha", "tasha@mail.ru", "sasha", Role.USER)
    );

    public static List<User> listSortedName(List<User> users) {
        return users.stream().sorted(Comparator.comparing(AbstractNamedEntity::getName)).collect(Collectors.toList());
    }

}
