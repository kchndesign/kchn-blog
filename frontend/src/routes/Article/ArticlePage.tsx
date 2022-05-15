import React, { useEffect, useState } from 'react';
import Skeleton from 'react-loading-skeleton';
import { Navigate, useNavigate, useParams } from 'react-router-dom';
import { Article, getArticleByUrl } from '../../services/api';
import ReactMarkdown from 'react-markdown';

const ArticlePage: React.FC = () => {
    // get the url from the url

    const urlParams = useParams();

    // usenavigate in case of 404
    const navigate = useNavigate();

    // =================================
    // ARTICLE STATE
    // is the data of the article currently being read.
    // =================================

    const [article, setArticle] = useState<Article>();

    // fetch article as component mounts.
    // if article comes back null, usenavigate to go home or 404
    useEffect(() => {
        const initArticle = async () => {
            let maybeArticle = await getArticleByUrl(urlParams.url as string);
            if (maybeArticle === null) {
                return navigate('/');
            }
            setArticle(maybeArticle);
        };

        initArticle();
    }, []);

    return (
        <>
            <h1>{article?.title || <Skeleton />}</h1>
            <ReactMarkdown>
                {article?.content ||
                    ((<Skeleton count={15} />) as unknown as string)}
            </ReactMarkdown>
        </>
    );
};

export default ArticlePage;
