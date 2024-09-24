package example.springbatch.job;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.lang.NonNull;

public class BillingJob implements Job {

    private final JobRepository jobRepository;

    public BillingJob(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @NonNull
    @Override
    public String getName() {
        return "BillingJob";
    }

    @Override
    public void execute(@NonNull JobExecution execution) {
        System.out.println("processing billing information");
        execution.setStatus(BatchStatus.COMPLETED);
        execution.setExitStatus(ExitStatus.COMPLETED);
        this.jobRepository.update(execution);

        // case error
//        try {
//            throw new Exception("Unable to process billing information");
//        } catch (Exception exception) {
//            execution.addFailureException(exception);
//            execution.setStatus(BatchStatus.COMPLETED);
//            execution.setExitStatus(ExitStatus.FAILED.addExitDescription(exception.getMessage()));
//        } finally {
//            this.jobRepository.update(execution);
//        }
    }
}
