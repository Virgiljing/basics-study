# Elasticsearch整合springboot

## 1 SpringData介绍

Spring Data是一个用于简化数据库、非关系型数据库、索引库访问，并支持云服务的开源框架。其主要目标是使得对数据的访问变得方便快捷，并支持map-reduce框架和云计算数据服务。 Spring Data可以极大的简化JPA（Elasticsearch...）的写法，可以在几乎不用写实现的情况下，实现对数据的访问和操作。除了CRUD外，还包括如分页、排序等一些常用的功能。

Spring Data的官网：<http://projects.spring.io/spring-data/>



## 2 SpringData Elasticsearch介绍

Spring Data ElasticSearch 基于 spring data API 简化 elasticSearch操作，将原始操作elasticSearch的客户端API 进行封装 。Spring Data为Elasticsearch项目提供集成搜索引擎。Spring Data Elasticsearch POJO的关键功能区域为中心的模型与Elastichsearch交互文档和轻松地编写一个存储索引库数据访问层。

官方网站：<http://projects.spring.io/spring-data-elasticsearch/> 

| spring data elasticsearch | elasticsearch |
| ------------------------- | :-----------: |
| 3.2.x                     |     6.8.1     |
| 3.1.x                     |     6.2.2     |
| 3.0.x                     |     5.5.0     |

ElasticSearch不同版本之间差别较大，特别是5.x与6.x之间

本人采用的是 **ES6.8.1**，**spring-data-elasticsearch3.2.2**版本可以对应

**项目架构**

![1588332651722](img\1588332651722.png)

## 3 pom.xml 依赖

```java
elasticsearch 相关

<dependency>
    <groupId>org.elasticsearch</groupId>
    <artifactId>elasticsearch</artifactId>
    <version>6.8.1</version>
</dependency>
<dependency>	
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>transport</artifactId>
    <version>6.8.1</version>
</dependency>
 
 
<dependency>
       <groupId>org.springframework.data</groupId>
       <artifactId>spring-data-elasticsearch</artifactId>
       <version>3.2.2.RELEASE</version>
       <exclusions>
            <exclusion>
                <groupId>org.elasticsearch.plugin</groupId>
                <artifactId>transport-netty4-client</artifactId>
            </exclusion>
        </exclusions>
</dependency>


对数据处理需要用到的依赖


<!--fastjson-->
       <dependency>
           <groupId>com.alibaba</groupId>
           <artifactId>fastjson</artifactId>
           <version>1.2.51</version>
       </dependency>
       <dependency>
           <groupId>com.alibaba</groupId>
           <artifactId>druid-spring-boot-starter</artifactId>
           <version>1.1.14</version>
       </dependency>

       <dependency>
           <groupId>com.alibaba</groupId>
           <artifactId>fastjson</artifactId>
           <version>1.2.41</version>
       </dependency>
```

## 4 配置application.yml 文件

```yaml
server:
  port: 18085
spring:
  application:
    name: search
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/my_test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    username: root
    password: root
    
  data:
    elasticsearch:
      cluster-name: my-application
      cluster-nodes: 8.129.75.206:9300


```

## 5 SpringData Elasticsearch增删改查



使用SpringDataElasticsearch实现数据的增删改查操作。

### 5.1 创建Dao接口  

**NewMediaMapper** 需要继承 **ElasticsearchRepository**

```java
@Repository
public interface NewMediaMapper extends ElasticsearchRepository<NewMediaInfo,Integer> {

}

```



### 5.2 创建返回实体

```java
/**
 * @author libinhong
 * @date 2020/5/1
 */
@Data
@Document(indexName = "newinfo",type = "newMedia")
public class NewMediaInfo  implements Serializable {

   /**
     * 主键ID
     */
    @Field(type = FieldType.Keyword)
    private Integer id;
    /**
     * 文章标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;
    /**
     * 文章内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Field(type = FieldType.Date,pattern = "yyyy-MM-dd HH:mm:ss",format = DateFormat.custom)
    private Date createTime;

    
}

```



**关于上面类中使用的相关spring-data-elasticsearch注解的解释：**

```properties

@Document: 代表在定义ES中的文档document

indexName: 索引名称，一般为全小写字母，可以看成是数据库名称
type: 类型，可以看成是数据库表名

Document 注解其它配置

useServerConfiguration 是否使用系统配置
shards 集群模式下分片存储，默认分5片
replicas 数据复制几份，默认1份
refreshInterval 多久刷新数据，默认1s
indexStoreType 索引存储模式，默认FS
createIndex 是否创建索引，默认True,代表不存在indexName对应索引时，自动创建


@Field: 文档中的字段类型，对应的是ES中document的Mappings概念，是在设置字段类型

type = FieldType.Keyword 表示不使用分词
type = FieldType.Text 表示使用分词

type: 字段类型，默认按照java类型进行推断，也可以手动指定，通过FieldType枚举
index: 是否为每个字段创建倒排索引,默认true,如果不想通过某个field的关键字来查询到文档,设置为false即可
pattern: 用在日期上类型字段上 format = DateFormat.custom, pattern = “yyyy-MM-dd HH:mm:ss:SSS”
searchAnalyzer: 指定搜索的分词，ik分词只有ik_smart(粗粒度)和ik_max_word(细粒度)两个模式，具体差异大家可以去ik官网查看
analyzer: 指定索引时的分词器，ik分词器有ik_smart和ik_max_word
store: 是否存储到文档的_sourch字段中，默认false情况下不存储
```



### 5.3 创建 service 层

```java
/**
 * @author libinhong
 * @date 2020/5/1
 */
public interface NewMediaService {
    
    /***
     * 增加数据
     * @param newMedia
     */
    void save(NewMediaInfo newMedia);

    /**
     * 批量保存
     * @param newMedia
     */
    void saveAll(List<NewMediaInfo> newMedia);

    /**
     * 根据ID删除数据
     * @param newMedia
     */
    void delete(NewMediaInfo newMedia);

    /**
     * 查询所有
     * @return
     */
    Iterable<NewMediaInfo> findAll();

    /**
     * 分页查询
     * @param pageable：分页封装对象
     * @return
     */
    Page<NewMediaInfo> findAll(Pageable pageable);
}

```

### 5.4 创建Service实现类

```java
/**
 * @author libinhong
 * @date 2020/5/1
 */
@Slf4j
@Service
public class NewMediaServiceImpl implements NewMediaService {


    @Autowired
    private NewMediaMapper newMediaMapper;

    /***
     * 增加数据
     * @param newMedia
     */
    @Override
    public void save(NewMediaInfo newMedia) {
        newMediaMapper.save(newMedia);
    }

    /**
     * 批量保存
     * @param newMedia
     */
    @Override
    public void saveAll(List<NewMediaInfo> newMedia) {
        newMediaMapper.saveAll(newMedia);
    }

    /**
     * 根据ID删除数据
     * @param newMedia
     */
    @Override
    public void delete(NewMediaInfo newMedia) {
        newMediaMapper.delete(newMedia);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public Iterable<NewMediaInfo> findAll() {
        return newMediaMapper.findAll();
    }

    /**
     * 分页查询
     * @param pageable：分页封装对象
     * @return
     */
    @Override
    public Page<NewMediaInfo> findAll(Pageable pageable) {
        return newMediaMapper.findAll(pageable);
    }
}


```



### 5.5 启动类配置

```java
SpringBootApplication(scanBasePackages = "com.glorypty")
@MapperScan("com.glorypty.*.mapper")
@EnableAsync
@EnableElasticsearchRepositories(basePackages = "com.glorypty.domain.mapper")
//开启缓存
//@EnableCaching
public class ClientApplication {
    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * availableProcessors is already set to [12], rejecting [12]
         **/
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(ClientApplication.class, args);
    }


}

@EnableElasticsearchRepositories(basePackages = "com.glorypty.domain.mapper")
指定扫描的包
```



### 5.5  controller

```java
/**
 * @author libinhong
 * @date 2020/5/1
 */
@RequestMapping(value = "newMedia")
public class NewMediaController {

    @Autowired
    private NewMediaService newMediaService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 创建索引和映射信息
     */
    @PostMapping("/testCreateMapping")
    public void testCreateMapping(){
        elasticsearchTemplate.createIndex(NewMediaInfo.class);
        elasticsearchTemplate.putMapping(NewMediaInfo.class);
    }

    /**
     * 添加文档数据
     */
    @PostMapping("/testSave")
    public void testSave(){
        NewMediaInfo newMediaInfo = new NewMediaInfo();
        newMediaInfo.setId(1);
        newMediaInfo.setTitle("测试SpringData ElasticSearch");
        newMediaInfo.setContent("Spring Data ElasticSearch 基于 spring data API 简化 elasticSearch操作，将原始操作elasticSearch的客户端API 进行封装Spring Data为Elasticsearch Elasticsearch项目提供集成搜索引擎");
        newMediaService.save(newMediaInfo);
    }


    /**
     * 更新测试
     */
    @PostMapping("/testUpdate")
    public void testUpdate(){
        NewMediaInfo newMediaInfo = new NewMediaInfo();
        newMediaInfo.setId(1);
        newMediaInfo.setTitle("elasticSearch 3.0版本发布...更新");
        newMediaInfo.setContent("ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口");
        newMediaService.save(newMediaInfo);
    }


    /**
     * 批量保存
     */
    @PostMapping("/testSaveAll")
    public void testSaveAll(){
        List<NewMediaInfo> newMediaInfos = new ArrayList<NewMediaInfo>();
        for (int i = 0; i <100 ; i++) {
            NewMediaInfo newMediaInfo = new NewMediaInfo();
            newMediaInfo.setId(i+1);
            newMediaInfo.setTitle((i+1)+"elasticSearch 3.0版本发布...更新");
            newMediaInfo.setContent((i+1)+"ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口");
            newMediaInfos.add(newMediaInfo);
        }
        newMediaService.saveAll(newMediaInfos);
    }

    /***
     * 根据ID删除
     */
    @DeleteMapping("testDelete")
    public void testDelete(){
        NewMediaInfo NewMediaInfo = new NewMediaInfo();
        NewMediaInfo.setId(100);
        newMediaService.delete(NewMediaInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/testFindAll")
    public void testFindAll(){
        //查询所有
        Iterable<NewMediaInfo> newMediaInfos = newMediaService.findAll();
        for (NewMediaInfo NewMediaInfo : newMediaInfos) {
            System.out.println(NewMediaInfo);
        }
    }

    /**
     * 分页查询
     */
    @PostMapping("/testFindAllPage")
    public void testFindAllPage(){
        //分页查询
        Page<NewMediaInfo> page = newMediaService.findAll(PageRequest.of(0, 3));
        //总记录数
        long totalElements = page.getTotalElements();
        //总页数
        int totalPages = page.getTotalPages();
        //获取数据
        List<NewMediaInfo> NewMediaInfos = page.getContent();
        for (NewMediaInfo NewMediaInfo : NewMediaInfos) {
            System.out.println(NewMediaInfo);
        }
    }
}

```



## 6 ES高级查询

ElasticsearchTemplate 可以实现索引库的增删改查[高级搜索]

2查询条件QueryBuilder的构建方法举例

### 6.1 精准查询（必须完全匹配）

```java
单个匹配termQuery
//不分词查询 参数1： 字段名，参数2：字段查询值，因为不分词，所以汉字只能查询一个字，英语是一个单词.
QueryBuilder queryBuilder=QueryBuilders.termQuery("fieldName", "fieldlValue");

//分词查询，采用默认的分词器
QueryBuilder queryBuilder2 = QueryBuilders.matchQuery("fieldName", "fieldlValue");

多个匹配

//不分词查询，参数1： 字段名，参数2：多个字段查询值,因为不分词，所以汉字只能查询一个字，英语是一个单词.
QueryBuilder queryBuilder=QueryBuilders.termsQuery("fieldName", "fieldlValue1","fieldlValue2...");

//分词查询，采用默认的分词器
QueryBuilder queryBuilder= QueryBuilders.multiMatchQuery("fieldlValue", "fieldName1", "fieldName2", "fieldName3");

//匹配所有文件，相当于就没有设置查询条件
QueryBuilder queryBuilder=QueryBuilders.matchAllQuery();

```



### 6.2 模糊查询（只要包含即可）

```java

//模糊查询常见的5个方法如下
//1.常用的字符串查询
QueryBuilders.queryStringQuery("fieldValue").field("fieldName");//左右模糊

//2.常用的用于推荐相似内容的查询
QueryBuilders.moreLikeThisQuery(new String[] {"fieldName"}).addLikeText("pipeidhua");//如果不指定filedName，则默认全部，常用在相似内容的推荐上

//3.前缀查询  如果字段没分词，就匹配整个字段前缀
QueryBuilders.prefixQuery("fieldName","fieldValue");

//4.fuzzy query:分词模糊查询，通过增加fuzziness模糊属性来查询,如能够匹配hotelName为tel前或后加一个字母的文档，fuzziness 的含义是检索的term 前后增加或减少n个单词的匹配查询
QueryBuilders.fuzzyQuery("hotelName", "tel").fuzziness(Fuzziness.ONE);

//5.wildcard query:通配符查询，支持* 任意字符串；？任意一个字符
QueryBuilders.wildcardQuery("fieldName","ctr*");//前面是fieldname，后面是带匹配字符的字符串
QueryBuilders.wildcardQuery("fieldName","c?r?");

```

### 6.3 范围查询

```java
//闭区间查询
QueryBuilder queryBuilder0 = QueryBuilders.rangeQuery("fieldName").from("fieldValue1").to("fieldValue2");

//开区间查询
QueryBuilder queryBuilder1 = QueryBuilders.rangeQuery("fieldName").from("fieldValue1").to("fieldValue2").includeUpper(false).includeLower(false);//默认是true，也就是包含

//大于
QueryBuilder queryBuilder2 = QueryBuilders.rangeQuery("fieldName").gt("fieldValue");

//大于等于
QueryBuilder queryBuilder3 = QueryBuilders.rangeQuery("fieldName").gte("fieldValue");

//小于
QueryBuilder queryBuilder4 = QueryBuilders.rangeQuery("fieldName").lt("fieldValue");

//小于等于
QueryBuilder queryBuilder5 = QueryBuilders.rangeQuery("fieldName").lte("fieldValue");

```

### 6.4 组合查询/多条件查询/布尔查询

```java
QueryBuilders.boolQuery()

QueryBuilders.boolQuery().must();//文档必须完全匹配条件，相当于and

QueryBuilders.boolQuery().mustNot();//文档必须不匹配条件，相当于not

QueryBuilders.boolQuery().should();//至少满足一个条件，这个文档就符合should，相当于or
```



### 6.5 字段过滤查询

```java
//定义不需要查询的字段
FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(null, new String[]{"name","name2"});

//定义需要查询的字段
FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(new String[]{"name","name2"}, null);
```

### 6.6 排序查询

```java
FieldSortBuilder sortBuilder = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
```



### 6.7 分页查询

```java
//查询构建器 NativeSearchQueryBuilder:搜索条件构建对象，用于封装各种搜索条件
NativeSearchQueryBuilder newSearchBuilder = new NativeSearchQueryBuilder();

//分页
Integer pageNum = 1;//页码
Integer pageSize = 5;//页大小

PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
newSearchBuilder.withPageable(pageRequest);
```



### 构建实例

```java
/**
 * @author libinhong
 * @date 2020/4/17
 */
@Slf4j
@Service
public class NewMediaServiceImpl implements NewMediaService {
	/**
     * 可以实现索引库的增删改查[高级搜索]
     */
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    /**
     * 多条件查询发布信息
     *
     * @param newQueryVo
     * @return
     */
    @Override
    public PageInfo<NewMediaInfo> newMediaSearch(NewMediaInfoQueryVo newQueryVo) {
        //搜索条件封装
        NativeSearchQueryBuilder newMediaSearchBuilder = buildBasicQuery(newQueryVo);
        //结果集查询
        PageInfo<NewMediaInfo> pageInfo = newMediaSearchList(newMediaSearchBuilder, newQueryVo);
        return pageInfo;
    }

    //查询条件封装
    private NativeSearchQueryBuilder buildBasicQuery(NewMediaInfoQueryVo newMediaQueryVo) {
        //查询构建器 NativeSearchQueryBuilder:搜索条件构建对象，用于封装各种搜索条件
        NativeSearchQueryBuilder newSearchBuilder = new NativeSearchQueryBuilder();
        ScoreSortBuilder scoreSortBuilder = new ScoreSortBuilder();
        //构建布尔查询
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        if (newMediaQueryVo != null) {
            //前端自定义不需要查询的字段
            if (newMediaQueryVo.getExcludes() != null && newMediaQueryVo.getExcludes().length > 0){
                newSearchBuilder.withSourceFilter(new FetchSourceFilter(null,newMediaQueryVo.getExcludes()));
            }
            //前端自定义需要查询的字段
            if (newMediaQueryVo.getIncludes() != null  && newMediaQueryVo.getIncludes().length > 0){
                newSearchBuilder.withSourceFilter(new FetchSourceFilter(newMediaQueryVo.getIncludes(),null));
            }
            //根据信息id查询
            if (StringUtils.isNotEmpty(newMediaQueryVo.getId())) {
                queryBuilder.must(QueryBuilders.matchQuery("_id", newMediaQueryVo.getId()));
            }
            //发布的信息平台
            if (newMediaQueryVo.getSourceType() != null && !newMediaQueryVo.getSourceType().equals("")) {
                queryBuilder.must(QueryBuilders.matchQuery("sourceType", newMediaQueryVo.getSourceType()));
            }
            //板块类型
            if (newMediaQueryVo.getPlateType() != null && !newMediaQueryVo.getPlateType().equals("")) {
                queryBuilder.must(QueryBuilders.matchQuery("plateType", newMediaQueryVo.getPlateType()));
            }
            //信息类型
            if (newMediaQueryVo.getInformationType() != null && !newMediaQueryVo.getInformationType().equals("")) {
                queryBuilder.must(QueryBuilders.matchQuery("informationType", newMediaQueryVo.getInformationType()));
            }
            //前端自定义查询条件
            if (newMediaQueryVo.getNewQueryVos() != null ) {
                List<NewQueryVo> newQueryVos = newMediaQueryVo.getNewQueryVos();
                for (NewQueryVo newQueryVo : newQueryVos) {
                    if (StringUtils.isBlank(newQueryVo.getNewQueryRule())) {
                        newQueryVo.setNewQueryRule("1");
                    }
                    if (newQueryVo.getNewQueryRule().equals("1")) {
                        if (StringUtils.isNotBlank(newQueryVo.getNewQueryValue())) {
                            if (newQueryVo.getNewQueryName().contains("tel")){
                                queryBuilder.must(QueryBuilders.wildcardQuery("object." + newQueryVo.getNewQueryName(), "*" + newQueryVo.getNewQueryValue() + "*"));
                            }else {
                                queryBuilder.must(QueryBuilders.matchPhraseQuery("object." + newQueryVo.getNewQueryName(), ""+newQueryVo.getNewQueryValue()));
                            }
                        }
                    }
                    if (newQueryVo.getNewQueryRule().equals("2")) {
                        queryBuilder.should(QueryBuilders.matchPhraseQuery("object." + newQueryVo.getNewQueryName(), ""+newQueryVo.getNewQueryValue()));
                    }
                    if (newQueryVo.getNewQueryRule().equals("3")) {
                        queryBuilder.mustNot(QueryBuilders.matchPhraseQuery("object." + newQueryVo.getNewQueryName(), ""+newQueryVo.getNewQueryValue()));
                    }
                }
            }
        }

        //默认排序
        newSearchBuilder.withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC));
        //前端自定义排序规则
        if (newMediaQueryVo.getNewSortVos() != null) {
            List<NewSortVo> newSortVos = newMediaQueryVo.getNewSortVos();
            for (NewSortVo newSortVo : newSortVos) {
                if (newSortVo.getNewSortRule().equals("1")) {
                    if (StringUtils.isNotBlank(newSortVo.getNewSortName())) {
                        if (getKeyWord(newSortVo.getNewSortName())) {
                            newSearchBuilder.withSort(SortBuilders.fieldSort("object." + newSortVo.getNewSortName() + ".keyword").order(SortOrder.ASC));
                        }else {
                            newSearchBuilder.withSort(SortBuilders.fieldSort("object." + newSortVo.getNewSortName()).order(SortOrder.ASC));
                        }
                    }
                }
                if (newSortVo.getNewSortRule().equals("2")) {
                    if (StringUtils.isNotBlank(newSortVo.getNewSortName())) {
                        if (getKeyWord(newSortVo.getNewSortName())) {
                            newSearchBuilder.withSort(SortBuilders.fieldSort("object." + newSortVo.getNewSortName() + ".keyword").order(SortOrder.DESC));
                        }else {
                            newSearchBuilder.withSort(SortBuilders.fieldSort("object." + newSortVo.getNewSortName()).order(SortOrder.DESC));
                        }
                    }
                }
            }
        }

        //前端自定义范围查询
        if (newMediaQueryVo.getNewRangeVos() != null){
            List<NewRangeVo> newRangeVos = newMediaQueryVo.getNewRangeVos();
            for (NewRangeVo newRangeVo : newRangeVos) {
                if (StringUtils.isNotBlank(newRangeVo.getNewRangeName())){
                    //.gte大于
                    if (StringUtils.isNotBlank(newRangeVo.getNewRangeStart())){
                        queryBuilder.must(QueryBuilders.rangeQuery(newRangeVo.getNewRangeName()).gt(newRangeVo.getNewRangeStart()));
                    }
                    //.lt小于
                    if (StringUtils.isNotBlank(newRangeVo.getNewRangeEnd())){
                        queryBuilder.must(QueryBuilders.rangeQuery(newRangeVo.getNewRangeName()).lte(newRangeVo.getNewRangeEnd()));
                    }
                }
            }
        }

        queryBuilder.must(QueryBuilders.matchQuery("isDelete", "0"));
        //分页
        Integer pageNum = newMediaQueryVo.getPageNum();//页码
        Integer pageSize = newMediaQueryVo.getPageSize();//页大小

        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        newSearchBuilder.withPageable(pageRequest);
        //添加过滤
        newSearchBuilder.withQuery(queryBuilder);
        return newSearchBuilder;
    }
    
    //执行查询
    private PageInfo<NewMediaInfo> newMediaSearchList(NativeSearchQueryBuilder newSearchBuilder, NewMediaInfoQueryVo newQueryVo) {
        AggregatedPage<NewMediaInfo> page = elasticsearchTemplate.queryForPage(
                        newSearchBuilder.build(),//搜索条件封装
                        NewMediaInfo.class//数据集合要转换的类型的字节码
                );
        //分页参数-总记录数
        long totalElements = page.getTotalElements();
        //总页数
        int totalPages = page.getTotalPages();
        //获取数据结果集
        List<NewMediaInfo> contents = page.getContent();

        PageInfo<NewMediaInfo> pageInfo = new PageInfo(contents, (int) totalElements);
        pageInfo.setTotal(totalElements);
        pageInfo.setPageNum(newQueryVo.getPageNum());
        pageInfo.setPageSize(newQueryVo.getPageSize());
        pageInfo.setPages(totalPages);
        pageInfo.setNavigatePages(8);
        return pageInfo;
    }

    //text类型开聚合排序功能
    public boolean getKeyWord (String stringName){
        if (stringName.contains("price") || stringName.contains("Price") || stringName.contains("deposit")){
            return true;
        }
        return false;
    }


}
```

