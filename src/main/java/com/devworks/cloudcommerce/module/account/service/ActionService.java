package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.model.Action;
import com.devworks.cloudcommerce.module.account.repository.ActionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ActionService {
    private final ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public Set<Action> getValidActions(Set<Action> actions) {
        var validActions = new HashSet<Action>();
        for (Action action : actions) {
            var existsAction = actionRepository.findByName(action.getName())
                .orElseThrow(() -> new NotFoundException("Action not found " + action.getName()));
            validActions.add(existsAction);
        }
        return validActions;
    }
}
