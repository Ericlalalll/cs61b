package synthesizer;

/**
 * @BelongsProject: hw1
 * @BelongsPackage: synthesizer
 * @Author: Eric
 * @CreateTime: 2022-07-10  02:43
 * @Description: TODO
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }
}
