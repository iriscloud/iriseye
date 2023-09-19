package me.zhengjie.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.zhengjie.modules.system.domain.Menu;
import me.zhengjie.modules.system.service.dto.MenuDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-19T19:52:56+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-5.6.3.jar, environment: Java 11.0.18 (OpenLogic)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu toEntity(MenuDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setCreateBy( arg0.getCreateBy() );
        menu.setUpdateBy( arg0.getUpdateBy() );
        menu.setCreateTime( arg0.getCreateTime() );
        menu.setUpdateTime( arg0.getUpdateTime() );
        menu.setId( arg0.getId() );
        menu.setTitle( arg0.getTitle() );
        menu.setComponentName( arg0.getComponentName() );
        menu.setMenuSort( arg0.getMenuSort() );
        menu.setComponent( arg0.getComponent() );
        menu.setPath( arg0.getPath() );
        menu.setType( arg0.getType() );
        menu.setPermission( arg0.getPermission() );
        menu.setIcon( arg0.getIcon() );
        menu.setCache( arg0.getCache() );
        menu.setHidden( arg0.getHidden() );
        menu.setPid( arg0.getPid() );
        menu.setSubCount( arg0.getSubCount() );
        menu.setIFrame( arg0.getIFrame() );

        return menu;
    }

    @Override
    public MenuDto toDto(Menu arg0) {
        if ( arg0 == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setCreateBy( arg0.getCreateBy() );
        menuDto.setUpdateBy( arg0.getUpdateBy() );
        menuDto.setCreateTime( arg0.getCreateTime() );
        menuDto.setUpdateTime( arg0.getUpdateTime() );
        menuDto.setId( arg0.getId() );
        menuDto.setType( arg0.getType() );
        menuDto.setPermission( arg0.getPermission() );
        menuDto.setTitle( arg0.getTitle() );
        menuDto.setMenuSort( arg0.getMenuSort() );
        menuDto.setPath( arg0.getPath() );
        menuDto.setComponent( arg0.getComponent() );
        menuDto.setPid( arg0.getPid() );
        menuDto.setSubCount( arg0.getSubCount() );
        menuDto.setIFrame( arg0.getIFrame() );
        menuDto.setCache( arg0.getCache() );
        menuDto.setHidden( arg0.getHidden() );
        menuDto.setComponentName( arg0.getComponentName() );
        menuDto.setIcon( arg0.getIcon() );

        return menuDto;
    }

    @Override
    public List<Menu> toEntity(List<MenuDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Menu> list = new ArrayList<Menu>( arg0.size() );
        for ( MenuDto menuDto : arg0 ) {
            list.add( toEntity( menuDto ) );
        }

        return list;
    }

    @Override
    public List<MenuDto> toDto(List<Menu> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MenuDto> list = new ArrayList<MenuDto>( arg0.size() );
        for ( Menu menu : arg0 ) {
            list.add( toDto( menu ) );
        }

        return list;
    }
}
