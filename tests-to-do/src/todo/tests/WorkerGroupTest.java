package todo.tests;

import blueprint.sdk.core.concurrent.JobQueue;
import blueprint.sdk.core.concurrent.WorkerGroup;
import blueprint.sdk.util.queue.Queue;

public class WorkerGroupTest {
	public static void main(String[] args) {
		Queue<Object> jobQueue = new JobQueue<Object>();
		WorkerGroup<Object, Queue<Object>> wg = new WorkerGroup<>(jobQueue, WorkerGroupTestWorker.class, 10);
		wg.start();

		while (true) {
			wg.addJob(new Object());
			try {
				Thread.sleep(100);
			} catch (InterruptedException ignored) {
			}
		}
	}
}