package forkNumberClasspath;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Verifies that {@code ${surefire.forkNumber}} inside an {@code additionalClasspathElement}
 * is expanded to the actual fork index at fork time.
 *
 * The pom.xml configures {@code additionalClasspathElement} as
 * {@code ${project.basedir}/fork-cp-${surefire.forkNumber}}.  For fork 1 this must resolve
 * to the {@code fork-cp-1/} directory, which contains {@code fork-marker.txt} with the
 * content {@code fork1}.
 */
public class ForkNumberClasspathTest {

    @Test
    public void forkNumberExpandedInClasspathElement() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("fork-marker.txt");
        assertNotNull("fork-marker.txt must be found via the fork-number-expanded classpath element", is);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            assertEquals("fork1", reader.readLine().trim());
        }
    }
}
