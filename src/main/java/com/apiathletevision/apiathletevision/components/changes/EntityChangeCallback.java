package com.apiathletevision.apiathletevision.components.changes;

import java.util.List;

public interface EntityChangeCallback {
    void onEntityChange(List<EntityChangeResultTable> entityChangesList);
}
