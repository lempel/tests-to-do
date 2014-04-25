package todo.tests;

import blueprint.sdk.core.concurrent.Worker;
import blueprint.sdk.util.queue.Queue;

public class WorkerGroupTestWorker extends Worker<Object> {
	public WorkerGroupTestWorker(Queue<Object> jobQueue, Object deathMonitor) {
		super(jobQueue, deathMonitor);
	}

	@Override
	protected void process(Object job) {
		System.out.println(hashCode());
	}
}
