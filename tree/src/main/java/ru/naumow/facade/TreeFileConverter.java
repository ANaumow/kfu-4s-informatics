package ru.naumow.facade;

import ru.naumow.tree.Tree;

public interface TreeFileConverter {

    Tree readTree(String filename);

    void writeTree(String filename, Tree tree);



}
