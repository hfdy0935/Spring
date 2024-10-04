import { defineComponent } from 'vue';

export default defineComponent({
    name: 'Layout',
    setup(props, { slots }) {
        return () => (
            <div class='w-screen h-screen flex flex-col justify-center items-center'>
                <div class='w-full h-24 text-center'>
                    <p class='font-black transition-all duration-200 text-3xl hover:text-5xl mb-8 select-none hover:bg-clip-text hover:bg-gradient-to-r from-sky-500 to-indigo-500 hover:text-transparent'>
                        用户系统
                    </p>
                </div>
                <div class='w-[90%] max-w-[800px] h-3/4 bg-slate-200 transition rounded-2xl hover:shadow-[0_0_20px_0_rgba(0,0,0,0.3)]'>
                    {slots.default?.()}
                </div>
            </div>
        );
    }
});
