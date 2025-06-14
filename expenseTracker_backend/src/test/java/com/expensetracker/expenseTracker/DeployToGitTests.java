package com.expensetracker.expenseTracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeployToGitTests {

    DeployToGit deployToGit = new DeployToGit();

    @Test
    public void testDeployToGit() {
        assertEquals(deployToGit.printDeployToGit(), "DEPLOYMENT", "Test 1 Passed");
    }
}
