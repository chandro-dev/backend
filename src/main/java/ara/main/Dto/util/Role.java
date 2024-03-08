package ara.main.Dto.util;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
@AllArgsConstructor
public enum Role {
    CUSTOMER(Arrays.asList(Permission.SEE_ALL_PRODUCT, Permission.PAYMENT_PRODUCT)),
    ADMINISTRATOR(Arrays.asList(Permission.SAVE_ONE_PRODUCT, Permission.SEE_ALL_PRODUCT));
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
