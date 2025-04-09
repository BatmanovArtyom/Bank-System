package Observer;

import lombok.NonNull;

public interface Observer<T> {
    void update(@NonNull T data);
}
