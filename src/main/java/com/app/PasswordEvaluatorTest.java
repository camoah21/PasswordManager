package main.java.com.app;

import org.junit.Test;
import static org.junit.Assert.*;
import main.java.com.app.PasswordEvaluator.PasswordStrength;

public class PasswordEvaluatorTest {
    @Test
    public void testEvaluatePasswordStrength() {
        // Test weak passwords
        assertEquals(PasswordStrength.WEAK, PasswordEvaluator.evaluatePasswordStrength("short"));
        assertEquals(PasswordStrength.WEAK, PasswordEvaluator.evaluatePasswordStrength("toolongtoolongtoolongtoolongtoolongtoolongtoolongtoolongtoolongtoolongtoolong"));

        // Test medium passwords
        assertEquals(PasswordStrength.MEDIUM, PasswordEvaluator.evaluatePasswordStrength("Longer123"));
        assertEquals(PasswordStrength.MEDIUM, PasswordEvaluator.evaluatePasswordStrength("Longer@123"));
        assertEquals(PasswordStrength.MEDIUM, PasswordEvaluator.evaluatePasswordStrength("LONGER@123"));

        // Test strong passwords
        assertEquals(PasswordStrength.STRONG, PasswordEvaluator.evaluatePasswordStrength("Password123!"));
        assertEquals(PasswordStrength.STRONG, PasswordEvaluator.evaluatePasswordStrength("MySecurePass1#"));

        // Test very strong passwords
        assertEquals(PasswordStrength.VERY_STRONG, PasswordEvaluator.evaluatePasswordStrength("VeryLongPassword123!"));
        assertEquals(PasswordStrength.VERY_STRONG, PasswordEvaluator.evaluatePasswordStrength("MySuperSecurePass1#"));
    }
}
