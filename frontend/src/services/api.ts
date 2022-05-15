const serverUrl = 'http://localhost:8080';

export interface Article {
    id: number;
    title: string;
    byline?: string;
    content: string;
    url: string;
    kicker?: string;
    metaTitle?: string;
    metaDesc?: string;
    published?: string;
    lastEdit?: string;
    author: string;
}

/**
 * Gets all posts from the server.
 *
 * @returns Array of articles
 */
export const getAllArticles = async (): Promise<Article[]> => {
    const resp: Response = await fetch(serverUrl + '/articles');
    const data: Article[] = await resp.json();
    console.log(data);
    return data;
};

/**
 * Gets an article by url
 *
 * @param url the specific url of the article to be fetched
 * @returns an Article or null if not found.
 */
export const getArticleByUrl = async (url: string): Promise<Article | null> => {
    const resp: Response = await fetch(serverUrl + '/articles/url/' + url);

    if (!resp.ok) {
        return null;
    }

    const data: Article = await resp.json();
    return data;
};

/**
 * Add Article to server.
 *
 * @param article to be added to server
 * @returns article from server
 */
export const addNewArticle = async (article: Article): Promise<Article> => {
    const fetchOptions: RequestInit = {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(article),
    };

    const resp: Response = await fetch(serverUrl + '/articles', fetchOptions);
    const data = await resp.json();

    return data;
};
