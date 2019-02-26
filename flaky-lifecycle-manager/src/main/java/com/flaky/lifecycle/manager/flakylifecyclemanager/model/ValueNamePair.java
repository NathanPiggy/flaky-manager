package com.flaky.lifecycle.manager.flakylifecyclemanager.model;

/**
 */
public final class ValueNamePair<V, N> {

    private V value;
    private N name;

    public ValueNamePair(V value, N name) {
        this.value = value;
        this.name = name;
    }

    public static final <V, N> ValueNamePair<V, N> of(V value, N name) {
        return new ValueNamePair(value, name);
    }

    public V value() {
        return this.value;
    }

    public N name() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueNamePair<?, ?> that = (ValueNamePair<?, ?>) o;

        if (!value.equals(that.value)) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{\"value\":" + this.value + ",\"name\":\"" + this.name + "\"}";
    }
}
