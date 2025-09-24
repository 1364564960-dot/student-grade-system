// ... existing code ...
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     // ... existing code ...
     * @return 角色列表
     */
    List<Role> findAll();

    Role findById(@Param("id") Integer id);
}
