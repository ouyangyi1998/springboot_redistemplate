# springboot_redistemplate
springboot结合redis，用redisUtil对redis进行操作
- 代码来自github jwwam的教学代码 sprigbootRedis 感谢
- 通过重写redistemplate，实现redis的分库查询，但是目前只修改了普遍缓存的分库，以后有时间加入其他缓存
- @MappedSuperclass可以让子实体类继承相同的属性
- @slfj4 可以不需要写 Logger log=new LoggerFactory.getclass(this.class)简化代码
- @Transactional 实现事务控制 在reidsTemplate中开启
- 代码重写了fastjson的序列化和反序列化，会导致 autoType is not support问题

```
1、JVM启动参数
-Dfastjson.parser.autoTypeSupport=true
2、代码中设置
ParserConfig.getGlobalInstance().setAutoTypeSupport(true); 
如果有使用非全局ParserConfig则用另外调用setAutoTypeSupport(true)；
```
 
 - controller继承自basecontroller包括对于log和ModelMap，uuid的操作模块
 
