package example.springbatch.job;

import org.springframework.batch.core.*;
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
    public void execute(JobExecution execution) {
        JobParameters jobParameters = execution.getJobParameters();
        String inputFile = jobParameters.getString("input.file");
        System.out.println("processing billing information from file " + inputFile);
        execution.setStatus(BatchStatus.COMPLETED);
        execution.setExitStatus(ExitStatus.COMPLETED);
        this.jobRepository.update(execution);
    }
}
