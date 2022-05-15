import React, { FormEventHandler, useState } from 'react';
import { addNewArticle, Article } from '../../services/api';
import StyledForm from '../../styled/Form/StyledForm';

// Typing the formstate object
type FormState = {
    title: string;
    byline?: string;
    content: string;
    kicker?: string;
    metaTitle?: string;
    metaDesc?: string;
    author: string;
};

const formInitialState: FormState = {
    title: '',
    byline: '',
    content: '',
    kicker: '',
    metaTitle: '',
    metaDesc: '',
    author: '',
};

const NewArticle = () => {
    // =================================
    // Form state management
    // Every time an input is changed, it changes the property corresponding to the input's htmlName.
    // =================================

    const [formState, setFormState] = useState(formInitialState);

    // use the input's name property to replace just the field that is changed.
    const handleFormChange = (e: React.SyntheticEvent) => {
        let input = e.target as HTMLInputElement;
        setFormState({
            ...formState,
            [input.name]: input.value,
        });
    };

    // when form is submitted, call the addNewPost service.
    const handleFormSubmit: FormEventHandler = async (
        e: React.BaseSyntheticEvent
    ) => {
        e.preventDefault();
        console.log(await addNewArticle(formState as Article));
    };

    // =================================
    // Start markup
    // =================================

    return (
        <>
            <h1>New Post</h1>
            <StyledForm onSubmit={handleFormSubmit}>
                {/* title */}
                <label htmlFor="title">Title: </label>
                <input
                    type="text"
                    name="title"
                    id="title"
                    onChange={handleFormChange}
                    required
                />

                {/* byline */}
                <label htmlFor="byline">Byline: </label>
                <input
                    type="text"
                    name="byline"
                    id="byline"
                    onChange={handleFormChange}
                />

                {/* content */}
                <label htmlFor="content">Body: </label>
                <textarea
                    name="content"
                    id="content"
                    onChange={handleFormChange}
                    required
                />

                {/* kicker */}
                <label htmlFor="kicker">Kicker: </label>
                <input
                    type="text"
                    name="kicker"
                    id="kicker"
                    onChange={handleFormChange}
                />

                {/* metatitle */}
                <label htmlFor="metaTitle">Meta Title: </label>
                <input
                    type="text"
                    name="metaTitle"
                    id="metaTitle"
                    onChange={handleFormChange}
                />

                {/* metaDesc */}
                <label htmlFor="metaDesc">Meta Description: </label>
                <input
                    type="text"
                    name="metaDesc"
                    id="metaDesc"
                    onChange={handleFormChange}
                />

                {/* author */}
                <label htmlFor="author">Author: </label>
                <input
                    type="text"
                    name="author"
                    id="author"
                    onChange={handleFormChange}
                    required
                />

                <div className=""></div>
                <button>reset</button>
            </StyledForm>
        </>
    );
};

export default NewArticle;
