package sww.lqw.tools.leetcode.work;

/**
 * Work with context
 * 
 * @author sww
 *
 */
public abstract class AbstractContextWork implements IWork {
	protected final WorkContext context;

	public AbstractContextWork() {
		this.context = WorkContext.getContext();
	}
}
