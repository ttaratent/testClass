db2 异常处理  ：
通过在一个begin  end 代码块中声明相应的异常处理器进行，指定类（SQLEXCEPTION、NOT FOUND、SQLWARNING）型的异常捕获

声明异常处理器
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN
 -----异常处理代码
END;
