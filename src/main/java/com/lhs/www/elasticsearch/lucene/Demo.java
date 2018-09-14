package com.lhs.www.elasticsearch.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import com.hankcs.lucene.HanLPAnalyzer;

/**
 * lucene 要求jdk1.8
 * @author LiHuaSheng
 *
 */
public class Demo {
	// 索引存储的地址
	private static final String INDEX_APTH = "F://ES//index";

	@Test
	public void addIndex() throws Exception{
		createIndex();
	}
	
	//添加索引
    public void createIndex() throws Exception{
    	//分析器
    	Analyzer analyzer = new StandardAnalyzer();
    	
    	//索引配置
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        //索引目录
        Directory directory = FSDirectory.open(Paths.get(INDEX_APTH));
        //索引
        IndexWriter indexWriter = null;

        try {
            
            indexWriter = new IndexWriter(directory,indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document doc1 = new Document();
        doc1.add(new StringField("id","1", Field.Store.YES));
        doc1.add(new StringField("title","banana", Field.Store.YES));
        doc1.add(new TextField("content","香蕉,3元一斤", Field.Store.YES));
        Document doc2 = new Document();
        doc2.add(new StringField("id","2", Field.Store.YES));
        doc2.add(new StringField("title","pearl", Field.Store.YES));
        doc2.add(new TextField("content","梨子,2元一斤", Field.Store.YES));
        Document doc3 = new Document();
        doc3.add(new StringField("id","3", Field.Store.YES));
        doc3.add(new StringField("title","snow pearl", Field.Store.YES));
        doc3.add(new TextField("content","雪梨,4元一斤", Field.Store.YES));
        try {
//
//            //添加索引
//            List<Document> documentList = Arrays.asList(doc1,doc2);
//            indexWriter.addDocuments(documentList);
//
//            //删除索引
//            indexWriter.deleteDocuments(new Term("fruitId","banana"));

            //更新索引
        	indexWriter.addDocument(doc1);
        	indexWriter.addDocument(doc2);
        	indexWriter.addDocument(doc3);
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                indexWriter.close();
                directory.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void searchIndex()throws Exception{
    	//索引目录
    	FSDirectory directory = FSDirectory.open(Paths.get(INDEX_APTH));
    	IndexReader reader = DirectoryReader.open(directory);//索引读入器
    	IndexSearcher indexSearcher = new IndexSearcher(reader);//索引搜索器
    	HanLPAnalyzer analyzer = new HanLPAnalyzer();//分词器
    	
    	String defaultStr = "content";//默认搜索字段类型，如果搜索字符串中没有指定，就使用默认的比如title:pearl
        QueryParser parser = new QueryParser(defaultStr,analyzer);//查询解析生成器
        
        //创建Query对象，把特定格式字符串解析得到
        String parseStr = "梨";
        Query query = parser.parse(parseStr);

        TopDocs topDocs = indexSearcher.search(query, 10);
        if(null != topDocs){
            System.out.println("find items :" + topDocs.totalHits);
            for(int i=0; i<topDocs.scoreDocs.length;i++){
                Document doc = indexSearcher.doc(topDocs.scoreDocs[i].doc);
                System.out.println("title : "+doc.get("title"));
                System.out.println("content : "+doc.get("content"));
            }
        }
        directory.close();
        reader.close();
    }
    
}
