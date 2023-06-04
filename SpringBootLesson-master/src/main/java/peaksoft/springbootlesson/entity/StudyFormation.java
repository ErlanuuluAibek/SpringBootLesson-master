package peaksoft.springbootlesson.entity;

import org.springframework.security.core.GrantedAuthority;

public enum StudyFormation implements GrantedAuthority {
    ONLINE,
    OFFLINE;

    @Override
    public String getAuthority() {
        return name();
    }
}
