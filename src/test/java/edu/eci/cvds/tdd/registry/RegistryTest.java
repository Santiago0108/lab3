package edu.eci.cvds.tdd.registry;

import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {
    private Registry registry = new Registry();

    @Test
    public void validateRegistryResult() {
        Person person = new Person();
        person.setAlive(true);
        person.setAge(20);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
    
    
    @Test
    public void validateDuplicated() {
        Person person = new Person("Ricardo", 12, 21, Gender.MALE, true);
        Person person1 = new Person("Ricardo", 12, 21, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        RegisterResult result1 = registry.registerVoter(person1);
        Assert.assertEquals(RegisterResult.DUPLICATED, result1);
    }

    @Test
    public void validateDead() {
        Person person = new Person("Ricardo", 12, 89, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void validateInvalid() {
        Person person = new Person("Ricardo", 12, -1, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validateInvalidBigNegative() {
        Person person = new Person("Ricardo", 12, -11321, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validateInvalidBig136() {
        Person person = new Person("Ricardo", 12, 136, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validateInvalidBigPositive() {
        Person person = new Person("Ricardo", 12, 136156, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validateUnder() {
        Person person = new Person("Ricardo", 12, 12, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void validateUndercero() {
        Person person = new Person("Ricardo", 12, 0, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void validateUnder17() {
        Person person = new Person("Ricardo", 12, 17, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void validate18() {
        Person person = new Person("Ricardo", 12, 18, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void validateValid() {
        Person person = new Person("Ricardo", 12, 21, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void validateValidBig() {
        Person person = new Person("Ricardo", 12, 135, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
}