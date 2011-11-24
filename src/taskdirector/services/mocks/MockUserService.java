
package taskdirector.services.mocks;

import java.util.*;
import taskdirector.services.interfaces.IUserService;

/**
 * In Memory implementation of the User Service
 * @author KallDrexx
 */
public class MockUserService implements IUserService {
    
    public MockUserService()
    {
    }
    
    @Override
    public UUID CreateUser(String username, String password) {     
        return UUID.randomUUID();
    }

    @Override
    public UUID Login(String username, String password) {
        return UUID.randomUUID();
    }

    @Override
    public void LogOut(UUID sessionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void ChangePassword(String username, String currentPassword, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }   
}