package ru.naumow.names.interpreter;

import ru.naumow.names.model.NameEntry;
import ru.naumow.names.storage.NameEntryRepository;

import java.util.ArrayList;
import java.util.List;

public class InterpreterCore {

    // add <имя>
    @CmdMapping("add")
    private void goTo(Environment env, String[] args) {
        CommandUtil.requireSize(args, 1);
        String name = args[0];
        NameEntryRepository nameStorage = env.getAttribute("nameStorage", NameEntryRepository.class);

        NameEntry nameEntry = NameEntry.builder()
                .name(name)
                .build();

        nameStorage.save(nameEntry);
    }

    // out
    @CmdMapping("out")
    private void out(Environment env, String[] args) {
        CommandUtil.requireSize(args, 0);
        NameEntryRepository nameStorage = env.getAttribute("nameStorage", NameEntryRepository.class);
        List<NameEntry> nameEntries = new ArrayList<>();
        nameStorage.findAll().forEach(nameEntries::add);
        System.out.println(nameEntries);
    }

}
