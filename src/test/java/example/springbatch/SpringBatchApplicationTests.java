package example.springbatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBatchTest
@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class SpringBatchApplicationTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @BeforeEach
    public void setUp() {
        this.jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void testJobExecution(CapturedOutput output) throws Exception {
        // given
        JobParameters jobParameters = this.jobLauncherTestUtils.getUniqueJobParametersBuilder()
                .addString("input.file", "/some/input/file")
                .toJobParameters();

        // when
        // ** Update the following line:
        JobExecution jobExecution = this.jobLauncherTestUtils.launchJob(jobParameters);

        // then
        Assertions.assertTrue(output.getOut().contains("processing billing information from file /some/input/file"));
        Assertions.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    }
}