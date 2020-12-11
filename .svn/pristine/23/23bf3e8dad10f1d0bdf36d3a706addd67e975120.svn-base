package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Bzresource;
import cn.stylefeng.guns.modular.hhsys.mapper.BzresourceMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.BzresourceParam;
import cn.stylefeng.guns.modular.hhsys.model.result.BzresourceResult;
import cn.stylefeng.guns.modular.hhsys.service.BzresourceService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-10-28
 */
@Service
public class BzresourceServiceImpl extends ServiceImpl<BzresourceMapper, Bzresource> implements BzresourceService {

    @Autowired
    private BzresourceMapper bzresourceMapper;

    @Override
    public void add(BzresourceParam param){
        Bzresource entity = getEntity(param);
        this.save(entity);
    }
    @Override
    public void addtBzresource(Bzresource param){
        this.save(param);
    }

    @Override
    public Map<String,Object> deleteById(String id){
        Map<String,Object> result = new HashMap<>();
        try {
            this.baseMapper.deleteById(id);
            result.put("flag",true);
        }catch (Exception e){
            result.put("flag",false);
            result.put("msg","表信息删除失败");
        }
        return result;
    }

    //@Transactional
    public Map<String,Object> insertBzresource(Map<String, Object> param){
        Map<String,Object> result = new HashMap<String,Object>();


        try {
            this.bzresourceMapper.insertBzresource(param);
            result.put("flag",true);
            result.put("detail","插入成功");
        }catch (Exception e){
            result.put("flag",false);
            result.put("detail","插入失败");
        }
        return result;
    }
//    @Override
//    public Map<String,Object> add(Map<String,Object> param){
//        return null;
//    }

    @Override
    public void delete(BzresourceParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(BzresourceParam param){
        Bzresource oldEntity = getOldEntity(param);
        Bzresource newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public BzresourceResult findBySpec(BzresourceParam param){
        return null;
    }

    @Override
    public BzresourceResult findByTablename(String tablename){
        return baseMapper.findByTableName(tablename);
    }

    @Override
    public List<BzresourceResult> findListBySpec(BzresourceParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(BzresourceParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public LayuiPageInfo findPageByUserid(String userid){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageListById(pageContext, userid);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BzresourceParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Bzresource getOldEntity(BzresourceParam param) {
        return this.getById(getKey(param));
    }

    private Bzresource getEntity(BzresourceParam param) {
        Bzresource entity = new Bzresource();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    //@Transactional
    public Map<String, Object> createMarkerTable(Map<String,Object> params){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            this.baseMapper.createMarkerTable(params);

            result.put("bool", true);
            result.put("detail","创建成功！");
        } catch (Exception ex) {
            result.put("bool", false);
            result.put("detail","创建失败！");
        }

        return result;
    }

    public Map<String, Object> deleteMarkerTable(String tableName){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            this.baseMapper.deleteMarkerTable(tableName);

            result.put("bool", true);
            result.put("detail","删除成功！");
        } catch (Exception ex) {
            result.put("bool", false);
            result.put("detail","删除失败！");
        }

        return result;
    }

    public List<Map<String, Object>> getDatas(String tablename){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tablename",tablename);
//        return this.baseMapper.selectByMap(map);
        return this.baseMapper.getDatas(tablename);
    }

    public Map<String, Object> deleteData(String tableName, String ID){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            this.baseMapper.deleteData(tableName,ID);

            result.put("bool", true);
            result.put("detail","删除成功！");
        } catch (Exception ex) {
            result.put("bool", false);
            result.put("detail","删除失败！");
        }

        return result;
    }

    public Map<String,Object> addDatas(String tableName, Map<String, Object> items){
        Map<String,Object> result = new HashMap<String, Object>();

        Map<String,Object> params = new HashMap<String,Object>();
        List<Object> itemList = new ArrayList<>();
        if(tableName == null && tableName == ""){
            result.put("bool", false);
            result.put("detail", "表名为空");
        }
        params.put("tablename",tableName);

        if(itemList.size() == 0) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("key", "id");
            UUID uuid = UUID.randomUUID();
            item.put("value", uuid.toString());
            itemList.add(item);
        }
        Set<String> ketSet = items.keySet();
        for(String key: ketSet){
            Map<String,Object> item = new HashMap<String, Object>();
            item.put("key",key);
            item.put("value",items.get(key));
            itemList.add(item);
        }


        if (itemList != null) {
            System.out.println(itemList);
            params.put("fields", itemList);
        }
        try {
            this.baseMapper.addData(params);

            result.put("bool", true);
            result.put("detail","添加成功！");
        } catch (Exception ex) {
            result.put("bool", false);
            result.put("detail","添加失败！");
        }

        return result;
    }

    public Map<String, Object> addDatas(Map<String,Object> data){
        Map<String,Object> result = new HashMap<>();

        Map<String,Object> params = new HashMap<String,Object>();
        List<Object> itemList = new ArrayList<>();
        String tableName = (String) data.get("tableName");
        params.put("tablename",tableName);

        final List<Map<String,Object>> fields = getFields(tableName);

        for(int i = 0; i < fields.size(); i++){
            if(fields.get(i).get("name") != null && fields.get(i).get("name") != ""){
                Map<String,Object> item = new HashMap<String,Object>();
                item.put("key", fields.get(i).get("name"));
                if(fields.get(i).get("name").equals("id")) {  // id传入的原始值是null，需要生成uuid
                    UUID uuid = UUID.randomUUID();
                    data.put("id",uuid);
                    data.remove("tableName");
                    result.put("data",data);
                    item.put("value", uuid.toString());
                } else {
//                    item.put("value",data.get(fields.get(i).get("name")));
                    {
                        switch (fields.get(i).get("type").toString()) {
                            case "VARCHAR":
                            case "TEXT":
                            case "LONGTEXT":
                                if (data.get(fields.get(i).get("name")) == null || StringUtils.equals("", data.get(fields.get(i).get("name")).toString()))
                                    item.put("value", "");
                                else
                                    item.put("value", data.get(fields.get(i).get("name")).toString());
                                break;
                            case "NUMERIC":
                            case "DOUBLE":
                                if (data.get(fields.get(i).get("name")) == null || StringUtils.equals("", data.get(fields.get(i).get("name")).toString()))
                                    item.put("value", 0);
                                else
                                    item.put("value", Double.valueOf(data.get(fields.get(i).get("name")).toString()));
                                break;
                            case "int4":
                            case "INT":
                                if (data.get(fields.get(i).get("name")) == null || StringUtils.equals("", data.get(fields.get(i).get("name")).toString()))
                                    item.put("value", 0);
                                else
                                    item.put("value", Integer.valueOf(data.get(fields.get(i).get("name")).toString()));
                                break;
                            case "TIMESTAMP":
                            case "TATE":
                                if (data.get(fields.get(i).get("name")) == null || StringUtils.equals("", data.get(fields.get(i).get("name")).toString()))
                                    item.put("value", 0);
                                else{
                                    try{
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                                        formatter.setLenient(false);
                                        java.util.Date date = formatter.parse(data.get(fields.get(i).get("name")).toString());
                                        item.put("value", new java.sql.Date(date.getTime()));
                                    }
                                    catch (Exception e){
                                        try{
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                            formatter.setLenient(false);
                                            java.util.Date date = formatter.parse(data.get(fields.get(i).get("name")).toString());
                                            item.put("value", new java.sql.Date(date.getTime()));
                                        }
                                        catch (Exception ee){
                                            try{
                                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                                                formatter.setLenient(false);
                                                java.util.Date date = formatter.parse(data.get(fields.get(i).get("name")).toString());
                                                item.put("value", new java.sql.Date(date.getTime()));
                                            }
                                            catch (Exception eee){

                                            }
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }

                itemList.add(item);
            }
        }

        if (itemList != null) {
            params.put("fields", itemList);
        }
        try {
            this.baseMapper.addData(params);

            result.put("bool", true);
            result.put("detail","添加成功！");
        } catch (Exception ex) {
            result.put("bool", false);
            result.put("detail","添加失败");
            result.remove("data");
        }

        return result;
    }
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public List<Map<String,Object>> getFields(String tableName){
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String,Object>> fields = new ArrayList<>();
        try{
            Map<String, Object> params = new HashMap<>();
            if(tableName != null && tableName!="") {
                params.put("tableName", tableName);
            }

            Connection conn = session.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + tableName);
            ResultSetMetaData metaData = rs.getMetaData();
            session.close();
//            List<Map<String,Object>> rs = getDatas(tableName);
//            ResultSetMetaData metaData = rs.getMetaData();
            for(int i = 1; i <= metaData.getColumnCount();i++){
                Map<String,Object> field = new HashMap<>();
                field.put("name",metaData.getColumnName(i));
                field.put("type",metaData.getColumnTypeName(i));
                field.put("isNullable",metaData.isNullable(i));
                fields.add(field);
            }
        }
        catch (Exception e){

        }
        return fields;
    }

}
