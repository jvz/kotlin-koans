package iv_properties

import util.TODO
import java.util.concurrent.atomic.AtomicBoolean

class LazyProperty(val initializer: () -> Int) {
    private val initialized = AtomicBoolean()
    private var value: Int = 0
    val lazy: Int
        get() {
            if (initialized.compareAndSet(false, true)) {
                value = initializer()
            }
            return value
        }
}

fun todoTask33(): Nothing = TODO(
    """
        Task 33.
        Add a custom getter to make the 'lazy' val really lazy.
        It should be initialized by the invocation of 'initializer()'
        at the moment of the first access.
        You can add as many additional properties as you need.
        Do not use delegated properties yet!
    """,
    references = { LazyProperty({ 42 }).lazy }
)
