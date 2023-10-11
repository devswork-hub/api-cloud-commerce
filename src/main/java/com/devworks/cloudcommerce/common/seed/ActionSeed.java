package com.devworks.cloudcommerce.common.seed;

import com.devworks.cloudcommerce.module.account.constants.ActionType;
import com.devworks.cloudcommerce.module.account.model.Action;
import com.devworks.cloudcommerce.module.account.repository.ActionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class ActionSeed {
    private final ActionRepository actionRepository;

    public ActionSeed(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @PostConstruct
    @Order(1)
    public void init() {
        Set<Action> actions = new HashSet<>();

        for (ActionType actionType : ActionType.values()) {
            if (!actionRepository.existsByName(actionType.getName())) {
                var a = new Action();
                a.setCreatedAt(LocalDateTime.now());
                a.setName(actionType.getName());
                a.setActive(true);
                actions.add(a);
            }
        }

        actionRepository.saveAll(actions);
    }
}
