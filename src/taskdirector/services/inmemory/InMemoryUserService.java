
package taskdirector.services.inmemory;

import java.util.UUID;
import taskdirector.services.interfaces.IUserService;

/**
 * In Memory implementation of the User Service
 * @author KallDrexx
 */
public class InMemoryUserService implements IUserService {

    @Override
    public UUID CreateUser(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UUID Login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
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
