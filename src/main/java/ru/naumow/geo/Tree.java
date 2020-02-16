package ru.naumow.geo;

import lombok.*;

import java.util.Iterator;

@Data
public class Tree {
    @NonNull final GeoNode           root;
    @NonNull       Iterator<GeoNode> iterator;

}
